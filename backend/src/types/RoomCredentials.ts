import { objectType } from "@nexus/schema";

export const RoomCredentials = objectType({
  name: "RoomCredentials",
  definition(t) {
    t.model.password();
    t.model.roomId();
  },
});
