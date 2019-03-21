node {
    properties([parameters([string(defaultValue: '127.0.0.1', description: 'Please, provide an IP to host a Website', name: 'IP', trim: false)])])
    stage("Install git"){
        sh "ssh ec2-user@${IP}    sudo yum install git python-pip -y"
    }
     stage("Clone a repo"){
        git "git@github.com:tilenbaeva/Flaskex.git"
    }
    stage("Copy files"){
        sh "scp -r * ec2-user@${IP}:/tmp/"
    }
    stage("Install requirements"){
        sh "ssh ec2-user@${IP}     sudo  pip install -r /tmp/requirements.txt"
    }
    stage("Run App"){
        sh "ssh ec2-user@${IP}  python /tmp/app.py"
    }
}