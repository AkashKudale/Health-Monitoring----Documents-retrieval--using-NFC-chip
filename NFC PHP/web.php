<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', 'AllPageController@showMainPage');
Route::get('/aboutus', 'AllPageController@showAboutus');
Route::get('/blog', 'AllPageController@showBlogs');
Route::get('/course/{course}', 'AllPageController@showCourse');
Route::get('/contactus', 'AllPageController@showContactUs');


















Route::get('/a/admin', function () {
    return view('/admin/admin_dashboard');
});


Route::get('/blog/{title}', 'Controller@getFullBlog');

Route::post('/postComment', 'Controller@postComment');

Route::get('/admin/blogs', 'AdminController@getBlogs');

Route::get('/admin/addBlog', function () {
    return view('/admin/add_blog');
});

Route::post('/admin/addblog', 'AdminController@addBlog');


Route::get('/admin/editblog/{title}', 'AdminController@getEditBlog');
Route::post('/admin/editblog', 'AdminController@editBlog');

Route::get('/admin/deleteblog/{title}', 'AdminController@deleteBlog');

Route::get('/admin/comments', 'AdminController@getComments');


Route::get('/admin/comment/changestatus/{id}', 'AdminController@changeStatus');

Route::get('/admin/comment/deletecomment/{id}', 'AdminController@deleteComment');

Route::get('/admin/socialmedia', 'SocialMediaController@getLinks');
Route::post('/admin/editLinks', 'SocialMediaController@editLinks');




Route::get('/admin/tabs/{tab}', 'TabsController@showCourses');
Route::post('/admin/add_heading', 'TabsController@addCourseHeading');
Route::get('/admin/tabs/{tab}/addheading', 'TabsController@getTabId');
Route::get('/admin/tabs/{tab}/{heading}/edit', 'TabsController@getEditHeading');
Route::get('/admin/tabs/{tab}/{heading}/delete', 'TabsController@deleteHeading');
Route::post('/admin/editHeading', 'TabsController@editHeading');





Route::get('/admin/tabs/{tab}/addsubheading', 'TabsController@getTabandHeadings');
Route::post('/admin/addcourse/que', 'TabsController@addsubheading');
Route::post('/admin/addcourse/track', 'TabsController@addQuestions');
Route::post('/admin/addcourse/syllabus', 'TabsController@addTrack');
Route::post('/admin/addcourse/course_whyus', 'TabsController@addSyllabus');
Route::post('/admin/addcourse/whyus/add', 'TabsController@addWhyus');
Route::get('/admin/tabs/{tab}/{id}/editcourse', 'TabsController@editCourse');
Route::post('/admin/editcourse/{id}/cou', 'TabsController@editC');
Route::get('/admin/editcourse/{tab_name}/{id}/que', 'TabsController@showQ');
Route::post('/admin/{tab_name}/{id}/que', 'TabsController@editQ');
Route::get('/admin/editcourse/{tab_name}/{id}/cer', 'TabsController@showC');
Route::post('/admin/{tab_name}/{id}/cer', 'TabsController@editCert');
Route::get('/admin/editcourse/{tab_name}/{id}/syl', 'TabsController@showSyl');
Route::post('/admin/{tab_name}/{id}/syl', 'TabsController@editSyl');
Route::get('/admin/editcourse/{tab_name}/{id}/why', 'TabsController@showWhy');
Route::post('/admin/{tab_name}/{id}/why', 'TabsController@editWhy');











Route::get('/admin/videourl', 'VideoController@getVideoUrl');
Route::post('/admin/videourl/editurl', 'VideoController@setVideoUrl');



Route::get('/admin/contactnumbers', 'ContactNumbersController@getNumbers');
Route::get('/admin/contactnumber/{contact_id}/edit', 'ContactNumbersController@getNumber');
Route::post('/admin/contactnumber/edit', 'ContactNumbersController@setNumber');


Route::get('/admin/aboutus', 'AboutUsController@getData');
Route::post('/admin/aboutus/edit', 'AboutUsController@setData');

Route::get('/admin/contactus', 'ContactUsController@getData');
Route::get('/admin/contactus/timings/{id}/edit', 'ContactUsController@getTiming');
Route::post('/admin/contactus/timings/edit', 'ContactUsController@setTiming');


Route::get('/admin/contactus/address/add', function () {
    return view('/admin/add_address');
});
Route::post('/admin/contactus/address/add_address', 'ContactUsController@addAddress');
Route::get('/admin/contactus/address/{id}/edit', 'ContactUsController@getAddress');
Route::get('/admin/contactus/address/{id}/delete', 'ContactUsController@deleteAddress');
Route::post('/admin/contactus/address/edit', 'ContactUsController@setAddress');
Route::post('/admin/contactus/gif/edit', 'ContactUsController@setGif');




Route::get('/admin/partners', 'PartnerController@getImgs');
Route::get('/admin/partners/add', function () {
    return view('/admin/add_partner');
});
Route::post('/admin/partners/add_partner', 'PartnerController@addPartner');
Route::get('/admin/partners/{id}/edit', 'PartnerController@getId');
Route::post('/admin/partners/edit', 'PartnerController@editImage');
Route::get('/admin/partners/{id}/delete', 'PartnerController@deleteImg');

Route::get('/admin/testimonials', 'TestimonialsController@getTestimonials');
Route::get('/admin/testimonials/add', 'TestimonialsController@getCourses');
Route::post('/admin/testimonials/add_t', 'TestimonialsController@addTestimonial');
Route::get('/admin/testimonials/{id}/edit', 'TestimonialsController@getInformation');
Route::get('/admin/testimonials/{id}/delete', 'TestimonialsController@deleteTestimonial');
Route::post('/admin/testimonials/edit', 'TestimonialsController@editTestimonial');

Route::get('/admin/sliders', 'SliderController@getSliders');
Route::get('/admin/sliders/add', function () {
    return view('/admin/add_slider');
});
Route::post('/admin/sliders/add_s', 'SliderController@addSlider');
Route::get('/admin/slider/{id}/edit', 'SliderController@getSlider');
Route::post('/admin/sliders/edit', 'SliderController@editSlider');
Route::get('/admin/slider/{id}/delete', 'SliderController@deleteSlider');






