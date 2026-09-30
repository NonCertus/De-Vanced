/*
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/gmxmail/layout/HidePremiumUpgradeButton.kt
 */
package app.morphe.patches.gmxmail.layout

import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.util.returnEarly

@Suppress("unused")
val hidePremiumUpgradeButtonPatch = bytecodePatch(
    name = "Hide Premium upgrade button",
    description = "Hides the Premium upgrade button in the navigation drawer.",
) {
    compatibleWith(AppCompatibilities.GMX_MAIL)

    execute {
        if (packageMetadata.versionName == "9.17.2") {
            IsUpsellingPossibleFingerprint.method.returnEarly(false)

            // getDisplay() drives both the upsell button and the 72dp spacer that
            // reserves room for it, so overriding the getter hides both.
            EntryPointDisplayFingerprint.method.returnEarly(false)
        }
    }
}