#!/bin/bash
export PATH=$PATH:/opt/homebrew/bin:/usr/local/bin
set -e

echo "========================================="
echo "DEPLOIEMENT CLOUD AUTOMATISE"
echo "========================================="
echo ""

echo ">>> 1. Initialisation de Terraform..."
terraform init

echo ">>> 2. Création de l'infrastructure sur Google Cloud..."
terraform apply -auto-approve

IP=$(terraform output -raw instance_ip)
echo "========================================="
echo "Machine Virtuelle prête ! IP : $IP"
echo "========================================="

echo "$IP ansible_user=ubuntu" > inventory.ini

echo ">>> Attente de 15 secondes pour le démarrage du serveur SSH..."
sleep 15

echo ">>> Emballage du projet depuis notre ordinateur..." # Ça permet d'éviter de passer le projet en public
mkdir -p project
cp -R ../projetDevops ./project/
cp ../Dockerfile ./project/ 2>/dev/null || true
tar -czf project.tar.gz project/
rm -rf project/

echo ">>> 3. Configuration automatique de la VM via Ansible..."
ansible-playbook -i inventory.ini playbook.yml

echo ""
echo "========================================="
echo "DEPLOIEMENT TERMINE AVEC SUCCES"
echo "Vous pouvez vous connecter à la machine avec :"
echo "ssh -i ansible_key.pem ubuntu@$IP"
echo "========================================="
