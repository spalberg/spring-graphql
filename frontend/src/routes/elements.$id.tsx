import { createFileRoute } from "@tanstack/react-router";

export const Route = createFileRoute("/elements/$id")({
  component: ElementDetail,
  
});

function ElementDetail() {
  return (
    <div className="p-2">
      <h3>Element Detail!</h3>
    </div>
  );
}
