package com.example.googlespeech.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.googlespeech.components.UserGuard
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.googlespeech.R
import com.example.googlespeech.utils.decodeJWT
import com.example.googlespeech.utils.getAccessToken
import com.example.googlespeech.utils.logout

@Composable
fun ProfileScreen(navController: NavController) {
    UserGuard(navController) {
        val context = LocalContext.current
        val token = getAccessToken(context)
        val userData = decodeJWT(token)
        val firstName = userData?.get("first_name") as? String ?: "Guest"
        val email = userData?.get("email") as? String ?: "guest@example.com"

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(android.graphics.Color.parseColor("#f2f1f6"))),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConstraintLayout(
                Modifier
                    .height(250.dp)
                    .background(color = Color(android.graphics.Color.parseColor("#32357a")))
            ) {
                val (topImg, profile, title, back, pen) = createRefs()

                Image(painterResource(id = R.drawable.arc_3),
                    null,
                    Modifier
                        .fillMaxWidth()
                        .constrainAs(topImg) {
                            bottom.linkTo(parent.bottom)
                        })
                Image(painterResource(id = R.drawable.user_2),
                    null,
                    Modifier
                        .fillMaxWidth()
                        .constrainAs(profile) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(topImg.bottom)
                        })
                Text(text = "Profile", style = TextStyle(
                    color = Color.White, fontSize = 30.sp
                ), modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
                Image(painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .constrainAs(back) {
                            top.linkTo(parent.top, margin = 24.dp)
                            start.linkTo(parent.start, margin = 24.dp)
                        }
                        .clickable { navController.popBackStack() })
                Image(painterResource(id = R.drawable.write), null, Modifier.constrainAs(pen) {
                    top.linkTo(profile.top)
                    start.linkTo(profile.end)
                })
            }
            Text(
                text = firstName,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp),
                color = Color(android.graphics.Color.parseColor("#32357a"))
            )
            Text(
                text = email,
                fontSize = 18.sp,
                color = Color(android.graphics.Color.parseColor("#747679"))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 32.dp, bottom = 10.dp)
                    .height(55.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.btn_1),
                        null,
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .clickable { })
                }
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Notification",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.arrow),
                        null,
                        Modifier
                            .padding(end = 5.dp)
                            .clickable { })
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                    .height(55.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.btn_2),
                        null,
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .clickable { })
                }
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Calendar",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.arrow),
                        null,
                        Modifier
                            .padding(end = 5.dp)
                            .clickable { })
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                    .height(55.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.btn_3),
                        null,
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .clickable { })
                }
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Gallery",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.arrow),
                        null,
                        Modifier
                            .padding(end = 5.dp)
                            .clickable { })
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                    .height(55.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.btn_4),
                        null,
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .clickable { })
                }
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "My Playlist",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.arrow),
                        null,
                        Modifier
                            .padding(end = 5.dp)
                            .clickable { })
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                    .height(55.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.btn_5),
                        null,
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .clickable { })
                }
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Share",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.arrow),
                        null,
                        Modifier
                            .padding(end = 5.dp)
                            .clickable { })
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                    .height(55.dp)
                    .clickable { logout(context, navController) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.btn_6),
                        null,
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .clickable { })
                }
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Logout",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }
        }
    }
}