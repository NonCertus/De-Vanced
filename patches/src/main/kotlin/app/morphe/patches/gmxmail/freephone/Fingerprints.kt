/*
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/gmxmail/freephone/Fingerprints.kt
 */
package app.morphe.patches.gmxmail.freephone

import app.morphe.patcher.Fingerprint

internal object IsEuiccEnabledFingerprint : Fingerprint(
    definingClass = "Lcom/unitedinternet/portal/android/mail/esim/abstraction/EuiccManagerWrapper;",
    name = "isEuiccEnabled",
)

internal object IsEsimEnabledFingerprint : Fingerprint(
    definingClass = "Lcom/unitedinternet/portal/android/remoteconfig/models/EsimConfig;",
    name = "isEsimEnabled",
)