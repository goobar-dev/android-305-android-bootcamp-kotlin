# 🖥 Lab 21: Working with Permissions

## Objectives
1. Update the implementation of `GPSButton` to manage the permissions request flow for location permissions
2. Pan the map to the user's last location when the GPS icon is clicked with permissions
    1. Call `locationClient.requestLocationUpdates()` to start listening to location udpates
    2. Call ` locationClient.lastLocation.addOnSuccessListener{}` to immediately respond to an available last location
    3. If a location update is available, update the `state` property to use this new location

# 🖥 Lab 21 Hints: Working with Permissions

## 💡 Helpful Resources
1. [Android permissions request flow](https://developer.android.com/training/permissions/requesting#request-permission)
2. [Accompanist Permissions](https://google.github.io/accompanist/permissions/)
3. [Getting last location in Android](https://developer.android.com/training/location/retrieve-current)