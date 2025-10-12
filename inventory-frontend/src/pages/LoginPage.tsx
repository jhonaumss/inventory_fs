import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { setupInterceptors } from "../api/setupInterceptors";
import type { AuthResponse, LoginRequest } from "../types/auth";
import { FormContainer, Input, MessageOtherAction, PrimaryButton, SecondaryButton, Title, Wrapper } from "../components/StyledComponents";

function LoginPage() {
  const [form, setForm] = useState<LoginRequest>({ username: "", password: "" });
  const [error, setError] = useState<string>("");
  const navigate = useNavigate();
  const { login } = useAuth();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");

    try {
      const api = setupInterceptors(null);
      const res = await api.post<AuthResponse>("/auth/login", form);

      login(res.data.token);
      navigate("/dashboard");
    } catch {
      setError("Invalid username or password");
    }
  };
  const handleRegister = (): void => {
    navigate("/register");
  };

  return (
    <Wrapper>
      <FormContainer>
        <Title>Iniciar Sesión</Title>
        <form onSubmit={handleSubmit}>
          <Input type="text" name="username" placeholder="Nombre de usuario" value={form.username} onChange={handleChange} />
          <Input type="password" name="password" placeholder="Contraseña" value={form.password} onChange={handleChange} />
          <PrimaryButton type="submit">Ingresar</PrimaryButton>
        </form>
        {/* <MessageOtherAction>
          No tienes una cuenta?
        </MessageOtherAction>
        <SecondaryButton onClick={handleRegister} >Registrarse</SecondaryButton> */}
      </FormContainer>
    </Wrapper>
  );
}

export default LoginPage;
