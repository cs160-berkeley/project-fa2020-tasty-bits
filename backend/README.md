## Reproduce GAE compile.

In `yarn start` you have to do `yarn generate && [startCommand]` (prisma:generate and nexus:generate) because Google removes and redoes node_modules which means you have to regenerate all the prisma stuff for nexus-prisma-plugin.

## Reproduce GAE with CloudSql.

Nodejs app with flex env.

Add env variable with Postgres connection url, the same one on the schema.prisma file.

CloudSQL enable private ip.
For `beta_settings > cloud_sql_instances`, use `[connection-name]=tcp:5432`. Add 5432 as port to connection url (it's the default for the cloud proxy). Use private ip as the host name in the connection url.

Connection Url:
`postgresql://[username]:[passwordWithLettersAndNumbers]@[private-ip]:5432/[dbNameInPostgres]`

Make sure `[googleProjectId]@appspot.gserviceaccount.com` has CloudSQL Client permissions as well as Compute Network User in IAM.

Lastly make sure that you do a `yarn generate && yarn sync && [startServerCommand]` (prisma migrate up --experimental --schema [path]) so that the db actually has the models.
