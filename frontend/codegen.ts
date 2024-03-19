import type { CodegenConfig } from "@graphql-codegen/cli";

const config: CodegenConfig = {
  overwrite: true,
  schema: "../src/main/resources/graphql/schema.graphqls",
  documents: [
    "src/**/*.tsx",
    "src/**/*.ts",
  ],
  generates: {
    "src/gql/": {
      preset: "client",
      plugins: [],
    },
  },
};

export default config;
