# TOTP Password Generator

This project generates a Time-based One-Time Password (TOTP) using HMAC-SHA-512 as the hash function, based on RFC6238 specifications. The setup is specifically configured to use a 30-second time step and a shared secret concatenated from your email ID and the string "HENNGECHALLENGE003".

## How to Use

copy the code and paste in any java code editor

Find the email variable.

Replace the placeholder email with your own registered email ID.

Modify Shared Secret (if needed):

If your shared secret differs from "HENNGECHALLENGE003", replace the current secret string with the correct one in the code.
Run the Code:

Run the script to generate the TOTP based on the HMAC-SHA-512 algorithm.
