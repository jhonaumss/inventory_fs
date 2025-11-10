import { useEffect, useState } from "react";
import { fetchProducts, deleteProduct } from "../api/products";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { Button, Header, IconButton, Input2, PageContainer, Table, Td, Th, Title2, Tr } from "../components/StyledComponents";
import { ConfirmModal } from "../components/ConfirmModal";
import { FiEdit, FiShoppingCart, FiTrash } from "react-icons/fi";
import type { Product } from "../models/Products";
import { toast } from "react-toastify";

export default function ProductsPage() {
  const [products, setProducts] = useState<Product[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [search, setSearch] = useState("");
  const [selectedProduct, setSelectedProduct] = useState<Product | null>(null);
  const navigate = useNavigate();
  const { roles } = useAuth();

  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = async () => {
    const data = await fetchProducts();
    setProducts(data);
  };

  const handleDelete = async (product: Product) => {
    setSelectedProduct(product);
    setShowModal(true);
  };

  const handleCart = async (product: Product) => {
    toast.success(`Producto "${product.name}" agregado al carrito de ventas`);
  };
  const handleConfirmDelete = async () => {
    if (selectedProduct && selectedProduct.id !== undefined) {
      await deleteProduct(selectedProduct.id);
      setShowModal(false);
      setSelectedProduct(null);
      loadProducts();
    }
  };

  const handleCancelDelete = () => {
    setShowModal(false);
    setSelectedProduct(null);
  };

  return (
    <PageContainer>
      <Header>
        <Title2>Productos</Title2>
        {(roles.includes("ROLE_MANAGER")) && (
          <Button onClick={() => navigate("/products/new")}>Agregar Producto</Button>
        )}
      </Header>

      <Input2
        type="text"
        placeholder="Buscar Productos"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
      <Table>
        <thead>
          <tr>
            <Th>Nombre</Th>
            <Th>Tipo</Th>
            <Th>Marca</Th>
            <Th>Fecha de caducidad</Th>
            <Th>Cantidad</Th>
            {(roles.some(role => ["ROLE_MANAGER", "ROLE_SALES"].includes(role))) && (
              <Th>Acciones</Th>
            )}
          </tr>
        </thead>
        <tbody>
          {products.map((currentProduct) => (
            <Tr key={currentProduct.id}>
              <Td>{currentProduct.name}</Td>
              <Td>{currentProduct.type}</Td>
              <Td>{currentProduct.brand}</Td>
              <Td>{currentProduct.dueDate}</Td>
              <Td>{currentProduct.quantity}</Td>
              {(roles.includes("ROLE_MANAGER")) && (
                <td>
                  <IconButton onClick={() => navigate(`/products/edit/${currentProduct.id}`)}><FiEdit/></IconButton>
                  <IconButton onClick={() => handleDelete(currentProduct)}><FiTrash/></IconButton>
                </td>
              )}
              {(roles.includes("ROLE_SALES")) && (
                <td>
                  <IconButton onClick={() => handleCart(currentProduct)}><FiShoppingCart/></IconButton>
                </td>
              )}
            </Tr>
          ))}
        </tbody>
      </Table>
      {selectedProduct && (
        <ConfirmModal
          show={showModal}
          title="Confirmar borrar producto"
          message={`¿Estás seguro de eliminar el producto "${selectedProduct.name}"?`}
          onConfirm={handleConfirmDelete}
          onCancel={handleCancelDelete}
        />
      )}

    </PageContainer>
  );
}
