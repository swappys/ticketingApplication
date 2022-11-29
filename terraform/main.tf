resource "aws_instance" "ticketing-app-server" {
  ami = "ami-00d48a21603b2119b"
  instance_type = "t3.micro"
  vpc_security_group_ids = [aws_security_group.main.id]
  tags = {
    Name = "ticketing-app-server-instance"
  }
}

resource "aws_security_group" "main" {
  name        = "ticketing-app-server-sg"
  description = "ticketing-app-server-sg"
  vpc_id      = data.aws_vpc.default.id

  ingress {
    description = "SSH"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "APP"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
  }

  tags = {
    Name = "ticketing-app-server-sg"
  }
}

resource "null_resource" "wait-for-ssh" {
  provisioner "remote-exec" {
    connection {
      host = aws_instance.ticketing-app-server.private_ip
      user = var.SSH_USR
      password = var.SSH_PSW
    }
    inline =["true"]
  }
}

resource "null_resource" "ansible-apply" {
  depends_on = [null_resource.wait-for-ssh]
  triggers = {
    always = timestamp()
  }
  provisioner "local-exec" {
    command = <<EOF
cd ansible
ansible-playbook -i ${aws_instance.ticketing-app-server.private_ip}, ticketingapp.yml -e ansible_user=${var.SSH_USR} -e ansible_password=${var.SSH_PSW}
EOF
  }
}