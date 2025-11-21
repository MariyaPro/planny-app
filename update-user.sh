echo "Running update-user.sh script..."

CONTAINER_NAME="mongodb"
ROOT_USER="${MONGO_ROOT_USER}"
ROOT_PASSWORD="${MONGO_ROOT_PASSWORD}"
APP_USER="${MONGO_USER}"
APP_PASSWORD="${MONGO_PASSWORD}"
APP_DATABASE="${MONGO_DATABASE}"

echo "Waiting for MongoDB to be ready..."
until mongosh "mongodb://${ROOT_USER}:${ROOT_PASSWORD}@${CONTAINER_NAME}:27017/admin" --eval "db.adminCommand('ping')" --quiet; do
  echo "MongoDB is not ready yet..."
  sleep 10
done

echo "MongoDB is ready. Checking application user..."

result=$(mongosh "mongodb://${ROOT_USER}:${ROOT_PASSWORD}@${CONTAINER_NAME}:27017/admin" --quiet --eval "
db = db.getSiblingDB('admin');
db.getUser('${APP_USER}')
")

if [[ "$result" =~ "null" ]]; then
    echo "User does not exist. Creating application user..."

    mongosh "mongodb://${ROOT_USER}:${ROOT_PASSWORD}@${CONTAINER_NAME}:27017/admin" --eval "
    db = db.getSiblingDB('${APP_DATABASE}');
    db.createCollection('init');

    db.getSiblingDB('admin').createUser({
      user: '${APP_USER}',
      pwd: '${APP_PASSWORD}',
      roles: [
        { role: 'readWrite', db: '${APP_DATABASE}' },
        { role: 'dbAdmin', db: '${APP_DATABASE}' }
      ]
    })
    "
    echo "Application user created successfully"
else
    echo "User already exists. Exiting..."
fi