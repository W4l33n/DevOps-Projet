provider "google" {
  credentials = file("/Users/bastienjacolin/Desktop/devops-uga-projet-7b94495e6adf.json")
  project     = "devops-uga-projet"
  region      = "europe-west1"
  zone        = "europe-west1-b"
}

# Règle de pare-feu pour le SSH
resource "google_compute_firewall" "ssh-rule" {
  name    = "allow-ssh-devops"
  network = "default"
  allow {
    protocol = "tcp"
    ports    = ["22"]
  }
  source_ranges = ["0.0.0.0/0"]
}

# Génération automatique d'une clé SSH locale sécurisée
resource "tls_private_key" "ssh" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

# Enregistrement de cette clé sur l'oridnateur (pour qu'Ansible puisse se connecter)
resource "local_file" "ssh_private_key_pem" {
  content         = tls_private_key.ssh.private_key_pem
  filename        = "${path.module}/ansible_key.pem"
  file_permission = "0600"
}

# Création de la Machine Virtuelle sur Google Cloud
resource "google_compute_instance" "vm_instance" {
  name         = "vm-devops-projet"
  machine_type = "e2-micro"

  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-2204-lts"
    }
  }

  network_interface {
    network = "default"
    access_config {
      // Attribue automatiquement une IP Publique
    }
  }

  # On pousse la clé publique dans les métadonnées de la VM
  metadata = {
    ssh-keys = "ubuntu:${tls_private_key.ssh.public_key_openssh}"
  }
}
