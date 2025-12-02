package br.com.fiap.lambda;

import br.com.fiap.lambda.FeedbackHandler;
import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackHandlerTest {

    private final FeedbackHandler handler = new FeedbackHandler();

    // Mock simples de Context
    private final Context mockContext = new Context() {
        @Override
        public String getAwsRequestId() { return "test-request"; }
        @Override
        public String getLogGroupName() { return "test-log-group"; }
        @Override
        public String getLogStreamName() { return "test-log-stream"; }
        @Override
        public String getFunctionName() { return "FeedbackLambda"; }
        @Override
        public String getFunctionVersion() { return "1.0"; }
        @Override
        public String getInvokedFunctionArn() { return "arn:aws:lambda:test"; }
        @Override
        public com.amazonaws.services.lambda.runtime.LambdaLogger getLogger() {
            return new com.amazonaws.services.lambda.runtime.LambdaLogger() {
                @Override
                public void log(String message) { System.out.println("LOG: " + message); }
                @Override
                public void log(byte[] message) { System.out.println("LOG: " + new String(message, java.nio.charset.StandardCharsets.UTF_8)); }
            };
        }

        @Override
        public int getRemainingTimeInMillis() { return 3000; }
        @Override
        public int getMemoryLimitInMB() { return 512; }
        @Override
        public com.amazonaws.services.lambda.runtime.CognitoIdentity getIdentity() { return null; }
        @Override
        public com.amazonaws.services.lambda.runtime.ClientContext getClientContext() { return null; }
    };

    @Test
    void deveRegistrarFeedbackPositivoSemNotificacao() {
        Feedback feedback = new Feedback();
        feedback.setUsuario("Beatriz");
        feedback.setNota(5);
        feedback.setComentario("Gostei bastante!");

        String resultado = handler.handleRequest(feedback, mockContext);

        assertEquals("Feedback registrado sem notificação.", resultado);
    }

    @Test
    void deveEnviarNotificacaoParaFeedbackNegativo() {
        Feedback feedback = new Feedback();
        feedback.setUsuario("Beatriz");
        feedback.setNota(2);
        feedback.setComentario("Atendimento demorou muito");

        String resultado = handler.handleRequest(feedback, mockContext);

        assertEquals("Feedback negativo detectado e notificação enviada.", resultado);
    }
}
