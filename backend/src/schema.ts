import { makeSchema } from "@nexus/schema";
import { nexusSchemaPrisma } from "nexus-plugin-prisma/schema";
import * as types from "./types";

export const schema = makeSchema({
  types,
  plugins: [
    nexusSchemaPrisma({
      outputs: {
        typegen: __dirname + "/generated/index.ts",
      },
    }),
  ],
  outputs: {
    schema: __dirname + "/../schema.graphql",
    typegen: __dirname + "/generated/nexus.ts",
  },
  typegenAutoConfig: {
    sources: [
      {
        source: "@prisma/client",
        alias: "prisma",
      },
      {
        source: require.resolve("./context"),
        alias: "Context",
      },
    ],
    contextType: "Context.Context",
  },
});
