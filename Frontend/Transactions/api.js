export const fetchWithAuth = async (url, payload) => {
  const token = sessionStorage.getItem("authToken");
  const headers = {
    ...payload.headers,
    Authorization: `Bearer ${token}`,
  };
  return fetch(url, { ...payload, headers });
};
