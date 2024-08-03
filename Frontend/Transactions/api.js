export async function loadConfig() {
  const response = await fetch('config.json');
  const config = await response.json();
  // alert(config['baseUrl'])
  return config['baseUrl'];
}

export const fetchWithAuth = async (url, payload) => {
  const token = sessionStorage.getItem("authToken");
  const headers = {
    ...payload.headers,
    Authorization: `Bearer ${token}`,
  };
  return fetch(await loadConfig() + url, { ...payload, headers });
};
