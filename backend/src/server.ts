import { GraphQLServer } from "graphql-yoga";
import { createContext } from "./context";
import { schema } from "./schema";

new GraphQLServer({ schema, context: createContext }).start(
  { debug: true, endpoint: "/graphql" },
  async () => {
    console.log(`🚀 Server ready at: http://localhost:4000`);
  }
);
