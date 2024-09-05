# HENNGE-TOTP-GENERATOR
To generate the TOTP password, follow RFC6238 with errata. Use TOTP with a 30-second time step (T0 = 0). The hash function should be HMAC-SHA-512. The shared secret is the user ID concatenated with the ASCII string "HENNGECHALLENGE003".
