db = db.getSiblingDB('orderconsumerdb');

db.createUser({
  user: "admin",
  pwd: "123",
  roles: [
    { role: "readWrite", db: "orderconsumerdb" },
    { role: "dbAdmin", db: "orderconsumerdb" }
  ]
});

print("******************************");
print("Usuário admin criado com sucesso!");
print("Banco orderconsumerdb inicializado!");
print("******************************");