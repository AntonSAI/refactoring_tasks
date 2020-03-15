package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {

    private RegisterAccountAction registerAccountAction;
    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) {
        try {
            isValidName(account);
            isValidPassword(account.getPassword());
        } catch (WrongAccountNameException en) {
            System.out.println("invalid name");
            en.printStackTrace();
        } catch (WrongPasswordException ep) {
            System.out.println("invalid password");
            ep.printStackTrace();
        }

        registerAccountAction.setAccountParameter(account);
    }

    private void setAccountParameter(Account account) {
        account.setCreatedDate(new Date());
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        account.setAddresses(addresses);
        accountManager.createNewAccount(account);
    }

    private void isValidName(Account account) {
        if (account.getName().length() <= 5){
            throw new WrongAccountNameException();
        }
    }

    private void isValidPassword(String password) {
        if (password.length() <= 8) {
            if (passwordChecker.validate(password) != OK) {
                throw new WrongPasswordException();
            }
        }
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {

        this.passwordChecker = passwordChecker;
    }
}
