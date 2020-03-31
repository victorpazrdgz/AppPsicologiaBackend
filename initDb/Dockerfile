FROM mongo

COPY initUsers.json /init.json

CMD mongoimport --uri "mongodb://172.20.0.2:27017/appPsicologia" --collection users --type json --file init.json --jsonArray
