CREATE USER 'max'@'%' IDENTIFIED WITH caching_sha2_password BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'max'@'%' WITH GRANT OPTION;