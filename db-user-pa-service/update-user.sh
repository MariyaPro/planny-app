#!/bin/bash
echo "Running update-user.sh script..."

ROOT_USER=${MONGO_ROOT_USER}
ROOT_PASSWORD=${MONGO_ROOT_PASSWORD}
MONGO_HOST=${MONGO_HOST}
APP_USER=${MONGO_USER}
APP_PASSWORD=${MONGO_PASSWORD}
APP_DATABASE=${MONGO_DATABASE}

echo "Checking if user $APP_USER exists..."

result=$(mongosh "mongodb://$ROOT_USER:$ROOT_PASSWORD@$MONGO_HOST:27017/admin?authSource=admin" --quiet --eval "
try {
  var user = db.getUser('$APP_USER');
  if (user) {
    print('USER_EXISTS');
  } else {
    print('USER_NOT_EXISTS');
  }
} catch (e) {
  print('ERROR: ' + e.message);
}
")
echo "result: $result"

if [[ "$result" == "USER_NOT_EXISTS" ]]; then
    echo "User does not exist. Start to creating $APP_USER user"

    mongosh "mongodb://$ROOT_USER:$ROOT_PASSWORD@$MONGO_HOST:27017/admin?authSource=admin" --eval "
    db.createUser({
      user: '$APP_USER',
      pwd: '$APP_PASSWORD',
      roles: [
        { role: 'readWrite', db: '$APP_DATABASE' },
        { role: 'dbAdmin', db: '$APP_DATABASE' }
      ]
    });
    print('User $APP_USER created successfully');
    "
else
    echo "User $APP_USER exists. Exiting..."
fi

resultalter=$(mongosh "mongodb://$ROOT_USER:$ROOT_PASSWORD@$MONGO_HOST:27017/admin?authSource=admin" --quiet --eval "
try {
  var user = db.getUser('$APP_USER');
  if (user) {
    print('USER_EXISTS');
  } else {
    print('USER_NOT_EXISTS');
  }
} catch (e) {
  print('ERROR: ' + e.message);
}
")
echo "alter result $APP_USER : $resultalter"
