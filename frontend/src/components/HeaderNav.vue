<template>
        <nav class="navbar navbar-expand-lg navbar-light bg-body-tertiary sticky-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">eCapital</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>

                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <router-link to="/" ><span class="material-icons">Employee</span></router-link>
                        </li>
                        <li class="nav-item">
                            <router-link to="/"><span class="material-symbols-outlined">Help</span></router-link>
                        </li>
                    </ul>
                    
                </div>
                <form class="d-flex ms-auto" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </nav>
</template>

<script>
export default {
    name: "HeaderNav",
    
}
</script>

<style>
.navbar-nav .nav-item {
    margin-right: 25px; 
}
.navbar-nav .nav-item a:hover,
.navbar-nav .nav-item router-link:hover {
    text-decoration: none;
}
.navbar-nav .nav-item a,
.navbar-nav .nav-item router-link {
    color: inherit; 
}
</style>