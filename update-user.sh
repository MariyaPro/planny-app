echo "Running update-user.sh script..."

CONTAINER_NAME="mongodb"
USERNAME="${MONGO_USER}"
PASSWORD="${MONGO_PASSWORD}"

result=$(mongosh mongodb://$CONTAINER_NAME:27017/ --quiet --eval "db.getUser(\"$USERNAME\")")

if [[ "$result" =~ "null" ]]; then
    echo "User does not exist. Start to creating default user"
    mongosh mongodb://$CONTAINER_NAME:27017/ --eval "db.createUser({ user: \"$USERNAME\", pwd: \"$PASSWORD\", roles: [{ role: \"root\", db: \"admin\" }]})"
else
    echo "User exists. Exiting..."
fi