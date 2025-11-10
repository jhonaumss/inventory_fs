import styled from 'styled-components';

export const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: calc(100dvh - 64px);
  background-color: #f3f4f6;
`;

export const FormContainer = styled.div`
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;

  @media (max-width: 600px) {
    width: 80%;
  }
`;

export const Title = styled.h2`
  text-align: center;
  font-size: 1.5rem;
  color: #333;
`;

export const MessageOtherAction = styled.p`
  text-align: center;
  font-weight: bold;
  margin-top: 10px;
  margin-bottom: 5px;
  color: #333;
`;

export const Input = styled.input`
  padding: 10px;
  margin: 2px 0px 10px 0px;
  border: 1px solid #ddd;
  border-radius: 5px;
  width: 100%;
  box-sizing: border-box;
  
  &:focus {
    outline: none;
    border-color: #4f46e5;
  }
`;

export const InputPassword = styled.input`
  padding: 10px 40px 10px 10px; /* extra right padding for the icon */
  margin: 10px 0;
  border: 1px solid #ddd;
  border-radius: 5px;
  width: 100%;
  box-sizing: border-box;

  &:focus {
    outline: none;
    border-color: #4f46e5;
  }
`;

 export const PasswordWrapper = styled.div`
  position: relative;
  width: 100%;
`;

export const TogglePassword = styled.button`
  position: absolute;
  top: 50%;
  right: 10px;
  transform: translateY(-50%);
  background: none;
  border: none;
  cursor: pointer;
  color: #6b7280;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0;

  &:hover {
    color: #4f46e5;
  }
`;

export const ErrorMessage = styled.div`
  background-color: #fee2e2;
  color: #b91c1c;
  border: 1px solid #fecaca;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 0.9rem;
  margin-bottom: 14px;
  text-align: center;
`;
export const ErrorText = styled.p`
  color: #dc2626;
  font-size: 0.85rem;
  margin: -5px 0 0 4px;
`;

export const Label = styled.label`
  font-size: 0.9rem;
  font-weight: 500;
  color: #374151;
`;

export const FormGroup = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 16px;
`;

export const PrimaryButton = styled.button`
  background-color: #4f46e5;
  color: white;
  padding: 12px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;
  margin-top: 10px;

  &:hover {
    background-color: #4338ca;
  }
`;

export const SecondaryButton = styled.button`
  background-color: transparent;
  color: #4f46e5;
  padding: 10px;
  border: 1px solid #4f46e5;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;
  margin-top: 0px;

  &:hover {
    background-color: #4f46e5;
    color: white;
  }
`;

export const ModalBackgroundAndPosition = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0,0,0,0.3);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 1000;
`;

export const ModalContainer = styled.div`
    background: #fff;
    padding: 30px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0,0,0,0.2);
    text-align: center;
`;
export const Select = styled.select`
  padding: 12px;
  margin: 10px 0;
  border: 1px solid #ddd;
  border-radius: 5px;
  width: 100%;
  box-sizing: border-box;

  &:focus {
    outline: none;
    border-color: #4f46e5;
  }
`;

export const PageContainer = styled.div`
  padding: 40px;
  background-color: #f8fafc;
  min-height: calc(100vh - 145px);
`;

export const Header = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
`;

export const Title2 = styled.h1`
  font-size: 1.8rem;
  font-weight: 700;
  color: #111827;
`;

export const Button = styled.button`
  background-color: #2563eb;
  color: white;
  border: none;
  border-radius: 8px;
  padding: 10px 18px;
  font-weight: 500;
  cursor: pointer;
  transition: 0.2s ease;

  &:hover {
    background-color: #1d4ed8;
  }
`;

export const Input2 = styled.input`
  width: 100%;
  padding: 10px 14px;
  border: 1px solid #d1d5db;
  border-radius: 8px;
  margin-bottom: 20px;
  font-size: 0.95rem;
  box-sizing: border-box;

  &:focus {
    outline: none;
    border-color: #2563eb;
    box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
  }
`;

export const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
  background-color: white;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.08);
`;

export const Th = styled.th`
  text-align: left;
  background-color: #f3f4f6;
  color: #374151;
  font-weight: 600;
  padding: 12px 16px;
  border-bottom: 1px solid #e5e7eb;
`;

export const Td = styled.td`
  padding: 12px 16px;
  color: #374151;
  border-bottom: 1px solid #f1f5f9;
`;

export const Tr = styled.tr`
  &:hover {
    background-color: #f9fafb;
  }
`;

export const IconButton = styled.button`
  background: transparent;
  border: none;
  cursor: pointer;
  padding: 6px;
  margin: 0 4px;
  border-radius: 4px;
  transition: background 0.2s;

  &:hover {
    background: #e2e8f0; // light gray on hover
  }

  svg {
    width: 18px;
    height: 18px;
    vertical-align: middle;
  }
`;
