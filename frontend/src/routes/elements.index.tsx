import { createFileRoute } from "@tanstack/react-router";
import { factory } from "../api/factory";
import { useSuspenseQuery } from "@tanstack/react-query";

const queryOptions = factory.elements.all();

export const Route = createFileRoute("/elements/")({
  loader: ({ context: { queryClient } }) =>
    queryClient.ensureQueryData(queryOptions),
  component: Elements,
});

function Elements() {
  const { data } = useSuspenseQuery(queryOptions);
  return (
    <div className="p-2">
      <h3>All the elements!</h3>
      <ul>
        {data.map((element) => (
          <li key={element.id}>{element.id}</li>
        ))}
      </ul>
    </div>
  );
}
