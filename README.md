# 📌 AWS Lambda - Feedback Handler

Este projeto implementa uma função **AWS Lambda em Java** para processar feedbacks de usuários.  
Se a nota for menor que 4, a função dispara uma notificação via **Amazon SNS**.  
Caso contrário, apenas registra o feedback.

---

## 🚀 Estrutura do Projeto

src/main/java/com/fiap/lambda/ 
── Feedback.java # Classe modelo do feedback 
── FeedbackHandler.java # Handler principal da Lambda


---

## ⚙️ Tecnologias

- **Java 17 (Corretto)**  
- **AWS Lambda**  
- **Amazon SNS**  
- **Maven** para build e empacotamento  

---

## 🔨 Build do Projeto

1. Compile e gere o JAR com todas as dependências:
   ```bash
   mvn clean package


