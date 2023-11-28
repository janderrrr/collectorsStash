package com.nashss.se.collectorsstash.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.collectorsstash.activity.request.CreateSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.CreateSeriesResults;

public class CreateSeriesLambda
        extends LambdaActivityRunner<CreateSeriesRequest, CreateSeriesResults>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateSeriesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateSeriesRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateSeriesRequest unauthenticatedRequest = input.fromBody(CreateSeriesRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateSeriesRequest.builder()
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withVolumeNumber(unauthenticatedRequest.getVolumeNumber())
                                    .withCustomerId(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateSeriesActivity().handleRequest(request)
        );
    }
}
