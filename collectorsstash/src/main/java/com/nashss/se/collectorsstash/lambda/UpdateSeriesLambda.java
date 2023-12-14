package com.nashss.se.collectorsstash.lambda;

import com.nashss.se.collectorsstash.activity.request.UpdateSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.UpdateSeriesResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class UpdateSeriesLambda
        extends LambdaActivityRunner<UpdateSeriesRequest, UpdateSeriesResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateSeriesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateSeriesRequest> input, Context context) {
        return super.runActivity(
            () -> {
                System.out.println("Received JSON payload: " + input.getBody());
                UpdateSeriesRequest unauthenticatedRequest = input.fromBody(UpdateSeriesRequest.class);
                return input.fromUserClaims(claims ->
                            UpdateSeriesRequest.builder()
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withVolumeNumber(unauthenticatedRequest.getVolumeNumber())
                                    .withSeriesId(unauthenticatedRequest.getSeriesId())
                                    .withCustomerId(claims.get("email"))
                                    .build()); }, (request, serviceComponent) ->
                        serviceComponent.provideUpdateSeriesActivity().handleRequest(request)
        );
    }
}
