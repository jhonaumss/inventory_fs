import React, { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { Link } from "react-router-dom";

const Navbar: React.FC = () => {
  const { roles, logout } = useContext(AuthContext);
  const isAdmin = roles.includes("ROLE_ADMIN");

  return (
    <nav className="navbar">
      <h2>Inventory App</h2>
      <ul>
        {isAdmin && <li><Link to="/users">Users</Link></li>}
        {!isAdmin && (
          <>
            <li><Link to="/products">Products</Link></li>
            <li><Link to="/orders">Orders</Link></li>
          </>
        )}
        <li><button onClick={logout}>Logout</button></li>
      </ul>
    </nav>
  );
};

export default Navbar;
