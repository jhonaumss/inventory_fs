import { getApi } from "./api";

export const setupInterceptors = (token: string | null, onUnauthorized?: () => void) => {
  const api = getApi();

  api.interceptors.request.use(
    (config) => {
      if (token) {
        if (!config.headers) {
          config.headers = {} as import("axios").AxiosRequestHeaders;
        }
        config.headers['Authorization'] = `Bearer ${token}`;
      }
      return config;
    },
    (error) => Promise.reject(error)
  );

  // Response interceptor
  api.interceptors.response.use(
    (response) => response,
    (error) => {
      if (error.response?.status === 401 && onUnauthorized) {
        onUnauthorized();
      }
      return Promise.reject(error);
    }
  );

  return api;
};
