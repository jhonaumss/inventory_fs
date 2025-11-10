import { Link, useNavigate } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import styled from "styled-components";

const NavbarContainer = styled.nav`
  background-color: #ffffff;
  border-bottom: 1px solid #e5e7eb;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 32px;
  height: 64px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
`;

const Brand = styled(Link)`
  font-size: 1.25rem;
  font-weight: 700;
  color: #111827;
  text-decoration: none;
  display: flex;
  align-items: center;
  gap: 8px;

  &:hover {
    color: #2563eb;
  }
`;

const RightSection = styled.div`
  display: flex;
  align-items: center;
  gap: 16px;
`;

const UserLabel = styled.span`
  color: #374151;
  font-weight: 500;
  text-transform: capitalize;
`;

const LogoutButton = styled.button`
  background-color: #f3f4f6;
  border: none;
  border-radius: 8px;
  color: #374151;
  padding: 8px 14px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.2s ease;

  &:hover {
    background-color: #e5e7eb;
  }
`;


export const Navbar = () => {
    const { user, roles, logout } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate("/login");
    };

    return (
        <NavbarContainer>
            <Brand to={roles.includes("ROLE_ADMIN") ? "/users" : "/products"}>
                🧾 Inventory App
            </Brand>
            <RightSection>
                <UserLabel>{user}</UserLabel>
                <LogoutButton onClick={handleLogout}>Cerrar Sesion</LogoutButton>
            </RightSection>
        </NavbarContainer>
    );
};

