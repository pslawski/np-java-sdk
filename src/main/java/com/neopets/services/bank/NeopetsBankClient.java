package com.neopets.services.bank;

import com.neopets.*;
import com.neopets.auth.NeopetsCredentials;
import com.neopets.services.bank.model.AlreadyClaimedInterestException;
import com.neopets.services.bank.model.CollectInterestRequest;
import com.neopets.services.bank.model.DepositNeopointsRequest;
import com.neopets.services.bank.model.GetBankRecordResult;
import com.neopets.services.bank.model.InvalidNeopointsAmountException;
import com.neopets.services.bank.model.WithdrawNeopointsRequest;
import com.neopets.services.bank.model.WithdrawalLimitException;
import com.neopets.services.bank.model.handlers.AlreadyClaimedInterestExceptionHandler;
import com.neopets.services.bank.model.transform.CollectInterestRequestMarshaller;
import com.neopets.services.bank.model.transform.DepositNeopointsRequestMarshaller;
import com.neopets.services.bank.model.transform.GetBankRecordResultUnmarshaller;
import com.neopets.services.bank.model.handlers.InvalidNeopointsAmountExceptionHandler;
import com.neopets.services.bank.model.handlers.WithdrawalLimitExceptionHandler;
import com.neopets.services.bank.model.transform.WithdrawNeopointsRequestMarshaller;
import com.neopets.transform.ErrorUnmarshaller;

public class NeopetsBankClient extends NeopetsClient implements NeopetsBank {

  public NeopetsBankClient(NeopetsCredentials credentials) {
    super(credentials);
  }

  @Override
  public GetBankRecordResult getBankRecord() {
    NeopetsRequest request = new NeopetsRequest(NeopetsURL.BANK);
    return invoke(request, new GetBankRecordResultUnmarshaller());
  }

  @Override
  public void depositNeopoints(int neopoints) throws InvalidNeopointsAmountException {
    depositNeopoints(new DepositNeopointsRequest(neopoints));
  }

  public void depositNeopoints(DepositNeopointsRequest originalRequest) throws InvalidNeopointsAmountException {
    NeopetsRequest request = new DepositNeopointsRequestMarshaller().marshall(originalRequest);
    invoke(request, new ErrorUnmarshaller())
            .handle(new InvalidNeopointsAmountExceptionHandler());
  }

  @Override
  public void withdrawNeopoints(int neopoints) throws InvalidNeopointsAmountException, WithdrawalLimitException {
    withdrawNeopoints(new WithdrawNeopointsRequest(neopoints));
  }

  public void withdrawNeopoints(WithdrawNeopointsRequest originalRequest)
          throws InvalidNeopointsAmountException, WithdrawalLimitException {
    NeopetsRequest request = new WithdrawNeopointsRequestMarshaller().marshall(originalRequest);
    invoke(request, new ErrorUnmarshaller())
            .handle(new InvalidNeopointsAmountExceptionHandler())
            .handle(new WithdrawalLimitExceptionHandler());
  }

  @Override
  public void collectInterest() throws AlreadyClaimedInterestException {
    collectInterest(new CollectInterestRequest());
  }

  public void collectInterest(CollectInterestRequest originalRequest) throws AlreadyClaimedInterestException {
    NeopetsRequest request= new CollectInterestRequestMarshaller().marshall(originalRequest);
    invoke(request, new ErrorUnmarshaller())
            .handle(new AlreadyClaimedInterestExceptionHandler());
  }

}

