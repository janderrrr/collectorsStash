package com.nashss.se.collectorsstash.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.collectorsstash.activity.request.RemoveSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.RemoveSeriesResult;

public class RemoveSeriesLambda
        extends LambdaActivityRunner<RemoveSeriesRequest, RemoveSeriesResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveSeriesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveSeriesRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    RemoveSeriesRequest unathenticatedRequest = input.fromPath(path ->
                            RemoveSeriesRequest.builder()
                                    .withSeriesId(path.get("seriesId"))
                                    .build());
                    return input.fromUserClaims(claims ->
                            RemoveSeriesRequest.builder()
                                    .withCustomerId(claims.get("email"))
                                    .withSeriesId(unathenticatedRequest.getSeriesId())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideRemoveSeriesActivity().handleRequest(request)
        );
    }
}
