#!/bin/sh

cd "$(dirname $0)"

# Create CA certificate and key

openssl genrsa -out ca.priv 2048

openssl req -x509 -new -nodes -sha256 -days 1825 -key ca.priv \
        -out ca.crt -subj "/C=US/O=mycompany/CN=ca"

# Create server certificate and key

openssl genrsa -out mycompany.priv 2048

openssl req -new -key mycompany.priv -out mycompany.csr \
        -subj "/C=US/O=mycompany/CN=mycompany.com"

cat <<EOF >mycompany.ext
subjectAltName = @alt_names
[alt_names]
DNS.1 = localhost
EOF

openssl x509 -req -in mycompany.csr -CA ca.crt -CAkey ca.priv \
        -out mycompany.crt -CAcreateserial  -days 1825 -sha256 \
        -extfile mycompany.ext

openssl pkcs12 -export -in mycompany.crt -inkey mycompany.priv \
        -out mycompany.p12 -certfile mycompany.crt \
        -password pass:changeit -name mycompany

openssl pkcs12 -in mycompany.p12 -out mycompany.pub \
        -clcerts -nokeys -passin pass:changeit
openssl pkcs12 -in mycompany.p12 -out mycompany.pem \
        -nodes -passin pass:changeit