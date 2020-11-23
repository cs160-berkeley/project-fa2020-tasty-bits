import { rule, shield } from 'graphql-shield';
import { getUserId } from '../utils';

//intentionally not checking if database entity corresponding user matches user id cus it's extra security work that won't be used
const rules = {
  defaultPass: rule()((parent, args, context) => {
    return true;
  }),
  isAuthenticatedUser: rule()((parent, args, context) => {
    const userId = getUserId(context);
    return Boolean(userId);
  }),
};

export const permissions = shield(
  {
    Query: rules.isAuthenticatedUser,
    Mutation: rules.isAuthenticatedUser,
  },
  { debug: true }
);
