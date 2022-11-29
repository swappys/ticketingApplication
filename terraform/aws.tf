provider "aws" {
  region = "us-east-1"
}

terraform {
  backend "s3" {
    bucket = "ticketing-app-server"
    key    = "terraform.tfstate"
    region = "us-east-1"
  }
}
