# AlumniConnect Functional Test Cases

## Purpose

This document reproduces the functional test cases and results from
Chapter Five, Table I (pages 26--27) of the thesis. Testing was
performed manually through the application interface and selected API
requests using Swagger. The tests were conducted on the working
prototype in a local development environment. A test is marked PASS
where the observed behaviour matched the expected outcome.

## Functional test cases

  ------------------------------------------------------------------------
  Test ID           Test scenario     Expected outcome   Actual result /
                                                         status
  ----------------- ----------------- ------------------ -----------------
  FT01              Register using    An alumni account  **PASS** ---
                    valid, unique     is created and can Registration and
                    account details   be used to access  account access
                                      the system.        worked as
                                                         expected.

  FT02              Register using an The system rejects **PASS** ---
                    existing email    the duplicate      Duplicate
                    address           registration.      registration was
                                                         rejected.

  FT03              Log in using      Authentication     **PASS** ---
                    valid credentials succeeds and the   Valid login
                                      user can access    worked as
                                      the appropriate    expected.
                                      account.           

  FT04              Log in using an   Authentication     **PASS** ---
                    incorrect         fails without      Incorrect
                    password          granting access.   credentials did
                                                         not grant access.

  FT05              Complete a        The alumni record  **PASS** --- The
                    profile after     is linked to the   profile could be
                    registration      registered         completed after
                                      account.           registration.

  FT06              Update an alumni  Valid changes are  **PASS** ---
                    profile           saved and appear   Profile updates
                                      when the profile   worked as
                                      is viewed again.   expected.

  FT07              Save an           The profile can be **PASS** --- The
                    unemployed alumni saved without      unemployed
                    profile without   employment details profile workflow
                    company or        when the alumnus   worked as
                    position details  is unemployed.     expected.

  FT08              Add and remove    Skill associations **PASS** ---
                    profile skills    reflect the        Skills could be
                                      changes without    added and removed
                                      duplicate entries. for the logged-in
                                                         alumnus.

  FT09              Create or update  Valid vacancy      **PASS** --- Job
                    a vacancy as an   details and        opportunities
                    administrator     required skills    were created and
                                      are saved          edited through
                                      correctly.         the administrator
                                                         interface.

  FT10              Deactivate a      Inactive job       **PASS** ---
                    vacancy and       opportunities and  Unchecking Active
                    exclude expired   job opportunities  Job Opportunity
                    job opportunities past their         removed the
                                      application        vacancy from both
                                      deadline are       alumni pages;
                                      excluded from      expired job
                                      alumni job         opportunities
                                      opportunities and  were also
                                      recommendations.   excluded.

  FT11              Submit invalid    The backend        **PASS** ---
                    data or request a returns an         Missing job
                    missing record    appropriate        title: HTTP 400;
                                      validation or      non-existent job
                                      not-found          ID 20: HTTP 404;
                                      response.          non-existent
                                                         alumni ID 99:
                                                         HTTP 404.

  FT12              Access protected  Access is denied.  **PASS** ---
                    functions without                    Protected access
                    a valid token                        was denied
                                                         without valid
                                                         authentication.

  FT13              Attempt an        Access is denied.  **PASS** --- An
                    administrative                       alumni token
                    operation using                      attempting to
                    an alumni account                    create a vacancy
                                                         returned HTTP 403
                                                         Forbidden.

  FT14              Attempt to access Access is denied   **PASS** --- The
                    another alumnus's and another user's request using
                    profile           profile is not     another alumnus's
                                      disclosed.         ID returned HTTP
                                                         403 Forbidden.
  ------------------------------------------------------------------------

## Additional observations

Inactive vacancies were excluded from the alumni job-opportunities list
and recommended jobs. Vacancies with past application deadlines were
also excluded, even when their active status remained checked. Expired
vacancies were excluded from the results, but their active status was
not automatically changed in the database.

Selected Swagger requests provided additional evidence: - Submitting a
job opportunity without a job title returned HTTP 400. - Requesting
non-existent job opportunity ID 20 returned HTTP 404. - Requesting
non-existent alumni ID 99 returned HTTP 404. - Using an alumni account
to create a job opportunity returned HTTP 403 Forbidden. - Attempting to
retrieve another alumnus's profile using a different alumni account
returned HTTP 403 Forbidden.

## Scope and limitations

These results relate to the specific test cases performed on the working
prototype. They do not establish that every possible invalid input or
security scenario has been tested. The tests were conducted using the
development database and test accounts, not in a live institutional
environment.

