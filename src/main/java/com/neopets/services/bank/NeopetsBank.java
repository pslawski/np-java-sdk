package com.neopets.services.bank;

import com.neopets.services.bank.model.AlreadyClaimedInterestException;
import com.neopets.services.bank.model.GetBankRecordResult;
import com.neopets.services.bank.model.InvalidNeopointsAmountException;
import com.neopets.services.bank.model.WithdrawalLimitException;

/**
 * Interface for accessing the Neopets Bank.
 */
public interface NeopetsBank {

  /**
   * Get the current record of your bank account.
   *
   * @return The parsed response from the Neopets bank page.
   */
  public GetBankRecordResult getBankRecord();

  /**
   * Deposit Neopoints to your bank account.
   *
   * @param neopoints
   *         The amount of Neopoints to deposit.
   *
   * @throws InvalidNeopointsAmountException
   *         If the amount requested exceeds the amount of neopoints on hand.
   */
  public void depositNeopoints(int neopoints) throws InvalidNeopointsAmountException;

  /**
   * Withdraw Neopoints from your bank account.
   *
   * @param neopoints
   *         The amount of Neopoints to withdraw.
   *
   * @throws InvalidNeopointsAmountException
   *         If the amount requested exceeds the current balance of your bank account.
   * @throws WithdrawalLimitException
   *         If the number of withdraws already made today exceeds the daily limit of 15.
   */
  public void withdrawNeopoints(int neopoints) throws InvalidNeopointsAmountException, WithdrawalLimitException;

  /**
   * Collect the daily interest from your bank account.
   *
   * @throws AlreadyClaimedInterestException
   *         If the interest was already claimed today.
   */
  public void collectInterest() throws AlreadyClaimedInterestException;

}

