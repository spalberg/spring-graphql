import { queryOptions } from "@tanstack/react-query";
import { getAllElements } from "./loaders";

export const factory = {
  elements: {
    _key: ["elements"],
    all: () =>
      queryOptions({
        queryKey: [...factory.elements._key],
        queryFn: getAllElements,
      }),
    byId: (id: string) =>
      queryOptions({
        queryKey: [...factory.elements._key, id],
        queryFn: async () => {
          return fetch(`/api/elements/${id}`).then((res) => res.json());
        },
      }),
  },
} as const;
