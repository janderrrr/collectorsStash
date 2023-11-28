package com.nashss.se.collectorsstash.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.collectorsstash.activity.request.GetSeriesRequest;
import com.nashss.se.collectorsstash.activity.results.GetSeriesResults;

public class GetSeriesLambda
        extends LambdaActivityRunner<GetSeriesRequest, GetSeriesResults>
        implements RequestHandler<AuthenticatedLambdaRequest<GetSeriesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetSeriesRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    return input.fromUserClaims(claims ->
                            GetSeriesRequest.builder()
                                    .withCustomerId(claims.get("email"))
                                    .build());
                },
        (request, serviceComponent) ->
                serviceComponent.provideGetSeriesActivity().handleRequest(request)
        );
    }
}

