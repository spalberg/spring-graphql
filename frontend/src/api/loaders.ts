import request from "graphql-request";
import { graphql } from "../gql";

const endpoint = "http://localhost:8080/graphql";

const allElementsQueryDocument = graphql(/* GraphQL */ `
  query allElements {
    elements {
      __typename
      id
    }
  }
`);

export async function getAllElements() {
  const { elements } = await request(endpoint, allElementsQueryDocument);
  return elements;
}
