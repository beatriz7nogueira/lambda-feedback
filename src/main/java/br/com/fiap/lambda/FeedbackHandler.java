package br.com.fiap.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FeedbackHandler implements RequestHandler<Feedback, String> {

    @Override
    public String handleRequest(Feedback feedback, Context context) {
        context.getLogger().log("Recebido feedback: " + feedback);

        if (feedback.getNota() < 4) {
            enviarNotificacao(feedback);
            return "Feedback negativo detectado e notificação enviada.";
        }

        return "Feedback registrado sem notificação.";
    }

    private void enviarNotificacao(Feedback feedback) {
// //       try (SnsClient snsClient = SnsClient.create()) {
//            String mensagem = "Feedback negativo recebido!\n" +
//                    "Usuário: " + feedback.getUsuario() + "\n" +
//                    "Nota: " + feedback.getNota() + "\n" +
//                    "Comentário: " + feedback.getComentario();
//
//            PublishRequest request = PublishRequest.builder()
//                    .topicArn("arn:aws:sns:us-east-1:123456789012:FeedbackNegativoTopic") // substitua pelo seu ARN
//                    .message(mensagem)
//                    .build();
//
//            snsClient.publish(request);

        System.out.println("Notificação simulada: " + feedback);

        }
    }

