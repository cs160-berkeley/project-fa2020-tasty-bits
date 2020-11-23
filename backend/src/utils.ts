import { verify } from 'jsonwebtoken';
import { Context } from './context';
import fs from 'fs';
interface Token {
  sub: string;
}

const signingFile = fs.readFileSync(__dirname + '/../appSigningFile.pem');

export const getUserId = (context: Context) => {
  const Authorization = context.request.get('Authorization');
  if (Authorization) {
    const token = Authorization.replace('Bearer ', '');
    const verifiedToken = verify(token, signingFile, {
      algorithms: ['RS256'],
      audience: 'https://calsurvivalguide.us.auth0.com/api/v2/',
      issuer: 'https://calsurvivalguide.us.auth0.com/',
    }) as Token;
    return verifiedToken && verifiedToken.sub;
  }
};
