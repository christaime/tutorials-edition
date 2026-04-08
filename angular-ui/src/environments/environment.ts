export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api', // Your Spring Boot API
  keycloak: {
    url: 'http://localhost:8080',      // Your Keycloak Server
    realm: 'tuto-editor',
    clientId: 'tuto-editor-ui'
  }
};
