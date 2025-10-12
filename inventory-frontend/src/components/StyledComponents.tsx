import styled from 'styled-components';

export const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100dvh;
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