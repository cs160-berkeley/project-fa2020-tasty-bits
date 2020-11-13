import { objectType } from "@nexus/schema";

export const Category = objectType({
  name: "Category",
  definition(t) {
    t.model.id();
    t.model.updatedAt();
    t.model.name();
    t.model.thumbnailUrl();
    t.model.rooms();
  },
});
