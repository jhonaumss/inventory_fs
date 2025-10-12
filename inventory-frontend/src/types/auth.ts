export interface LoginRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
  role: "Administrador" | "Trabajador";
}

export interface AuthResponse {
  token: string;
}
