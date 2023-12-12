package com.nashss.se.collectorsstash.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.collectorsstash.activity.request.CreateComicBookRequest;

import com.nashss.se.collectorsstash.activity.results.CreateComicBookResult;

public class CreateComicBooksLambda
        extends LambdaActivityRunner<CreateComicBookRequest, CreateComicBookResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateComicBookRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateComicBookRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateComicBookRequest unauthenticatedRequest = input.fromBody(CreateComicBookRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateComicBookRequest.builder()
                                    .withSeriesId(unauthenticatedRequest.getSeriesId())
                                    .withIssueNumber(unauthenticatedRequest.getIssueNumber())
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withVolumeNumber(unauthenticatedRequest.getVolumeNumber())
                                    .withIsFavorite(unauthenticatedRequest.getFavorite())
                                    .withPrice(unauthenticatedRequest.getPrice())
                                    .withPublisher(unauthenticatedRequest.getPublisher())
                                    .withYear(unauthenticatedRequest.getYear())
                                    .withCustomerId(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateComicBookActivity().handleRequest(request)

        );
    }
}
