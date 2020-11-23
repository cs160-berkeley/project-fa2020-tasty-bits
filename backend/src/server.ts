import { GraphQLServer } from 'graphql-yoga';
import { createContext } from './context';
import { schema } from './schema';
import { permissions } from './permissions';

new GraphQLServer({
  schema,
  context: createContext,
  middlewares: [permissions],
}).start({ debug: true, endpoint: '/graphql' }, async () => {
  console.log(`🚀 Server ready at: http://localhost:4000`);
});
